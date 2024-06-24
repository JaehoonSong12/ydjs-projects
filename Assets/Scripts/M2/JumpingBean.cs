using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class JumpingBean : MonoBehaviour
{
    private Rigidbody rb;
    public float minJumpForce = 5f;
    public float maxJumpForce = 10f;
    public float minJumpInterval = 1f;
    public float maxJumpInterval = 3f;
    private float nextJumpTime;
    private bool isGrounded;

    void Start()
    {
        rb = GetComponent<Rigidbody>();
        ScheduleNextJump();
    }

    void FixedUpdate()
    {
        if (Time.time >= nextJumpTime && isGrounded)
        {
            Jump();
            ScheduleNextJump();
        }
    }

    void ScheduleNextJump()
    {
        nextJumpTime = Time.time + Random.Range(minJumpInterval, maxJumpInterval);
    }

    void Jump()
    {
        float jumpForce = Random.Range(minJumpForce, maxJumpForce);
        Vector3 jumpDirection = new Vector3(Random.Range(-1f, 1f), 1, Random.Range(-1f, 1f)).normalized;
        rb.AddForce(jumpDirection * jumpForce, ForceMode.Impulse);
        float torque = Random.Range(-10f, 10f);
        rb.AddTorque(new Vector3(torque, torque, torque));
    }

    void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.CompareTag("ground"))
        {
            isGrounded = true;
        }
    }

    void OnCollisionExit(Collision collision)
    {
        if (collision.gameObject.CompareTag("ground"))
        {
            isGrounded = false;
        }
    }
}
